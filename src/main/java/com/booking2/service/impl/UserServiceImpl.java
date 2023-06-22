package com.booking2.service.impl;


import com.booking2.entities.User;
import com.booking2.payload.UserDTO;
import com.booking2.repository.UserRepository;
import com.booking2.service.UserService;
import com.booking2.util.CsvExplore;
import com.booking2.util.ExcelExporter;
import com.booking2.util.PdfExplorer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String uploadDirectory = "src/main/resources/static/user_profile_image/";

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        //it will save that time when you create
        user.setCreatedAt(new Date());
        //it will save that time when you update
        user.setUpdatedAt(new Date());

// If a password is provided, encode and set it
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            //if password is null then its empty in password column
            user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        }
        //this will get image
        MultipartFile profileImage = userDTO.getProfileImage();
        //chack profile image not null & not empty
        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = saveProfileImage(profileImage);
            user.setProfilePicture(fileName);
        }
        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override   // get
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        List<UserDTO> userDTOS =
                usersPage.stream().map(this::userToDto).collect(Collectors.toList());
        return new PageImpl<>(userDTOS, pageable, usersPage.getTotalElements());
    }

    @Override      // delete
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new
                RuntimeException("User not found"));
        userRepository.delete(user);
        // userRepository.deleteById(user.getId());
        // userRepository.deleteById(id);
    }

    @Override   // update
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        // Update the fields of the user object
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        // If a new password is provided, encode and set it
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        }
        // If a new profile image is provided, save it and set the filename
        MultipartFile profileImage = userDTO.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = saveProfileImage(profileImage);
            user.setProfilePicture(fileName);
        } // Set the update time
        user.setUpdatedAt(new Date());
        User updatedUser = userRepository.save(user);
        return userToDto(updatedUser);
    }

    @Override  // Excel
    public InputStreamResource getUserAsExcel() {
        List<User> users = userRepository.findAll();
        try {
            return ExcelExporter.exportUsersToExcel(users);
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate Excel file", e);
        }
    }

    @Override  // pdf Downloader
    public InputStreamResource getUserAsPdf() throws Exception {
        List<UserDTO> userDTOS = userRepository.findAll()
                .stream().map(e -> userToDto(e)).collect(Collectors.toList());
        ByteArrayInputStream pdfInputStream = PdfExplorer.exportUsersToPdf(userDTOS);
        return new InputStreamResource(pdfInputStream);
    }

    @Override  // CSV donloder
    public InputStreamResource getUserCsv() {
        List<UserDTO> userDTOS = userRepository.findAll().stream().map(e -> userToDto(e)).collect(Collectors.toList());
        ByteArrayInputStream pdfInputStream = CsvExplore.exportUsersToCsv(userDTOS);
        return new InputStreamResource(pdfInputStream);
    }


    private String saveProfileImage(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            String baseFileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
            String uniqueFileName = baseFileName + "_" + System.currentTimeMillis() + fileExtension;
            Path path = Paths.get(uploadDirectory + uniqueFileName);
            Files.write(path, bytes);
            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save profile image", e);
        }
    }

    //DTO to entity
//    private User dtoToUser(UserDTO userDTO) {
//        User user = new User();
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setEmail(userDTO.getEmail());
//        user.setPasswordHash(userDTO.getPassword());
//        user.setPhoneNumber(userDTO.getPhoneNumber());
//        return user;
//    }

    //DTO to entity Mapping
    User dtoToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }

    ///Entity to DTO Mapping
    UserDTO userToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
   // //Entity to DTO
//
//    private UserDTO userToDto(User user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setFirstName(user.getFirstName());
//        userDTO.setLastName(user.getLastName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPhoneNumber(user.getPhoneNumber());
//        userDTO.setProfilePicture(user.getProfilePicture());
//        userDTO.setCreatedAt(user.getCreatedAt());
//        userDTO.setUpdatedAt(user.getUpdatedAt());
//        return userDTO;
//    }


