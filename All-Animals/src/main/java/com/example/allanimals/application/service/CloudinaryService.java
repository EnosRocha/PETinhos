package com.example.allanimals.application.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public List<String> uploadImagens(List<MultipartFile> files) {
        return files.stream().map(file -> {
            try {
                Map resultado = cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap("folder", "pets")
                );
                return resultado.get("secure_url").toString();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao fazer upload: " + file.getOriginalFilename(), e);
            }
        }).toList();
    }


}
