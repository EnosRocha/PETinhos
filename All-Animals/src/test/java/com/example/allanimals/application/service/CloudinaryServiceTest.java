package com.example.allanimals.application.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CloudinaryServiceTest {

    @Mock private Cloudinary cloudinary;
    @Mock private Uploader uploader;

    @InjectMocks
    private CloudinaryService cloudinaryService;

    @Test
    void deveRetornarUrlsAposUpload() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});
        when(file.getOriginalFilename()).thenReturn("foto.jpg");
        when(cloudinary.uploader()).thenReturn(uploader);
        when(uploader.upload(any(byte[].class), any(Map.class)))
                .thenReturn(Map.of("secure_url", "https://cloudinary.com/foto.jpg"));

        List<String> urls = cloudinaryService.uploadImagens(List.of(file));

        assertEquals(1, urls.size());
        assertEquals("https://cloudinary.com/foto.jpg", urls.get(0));
    }

    @Test
    void deveLancarRuntimeExceptionQuandoFalharUpload() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getBytes()).thenThrow(new IOException("erro de leitura"));
        when(file.getOriginalFilename()).thenReturn("foto.jpg");
        when(cloudinary.uploader()).thenReturn(uploader);

        assertThrows(RuntimeException.class,
                () -> cloudinaryService.uploadImagens(List.of(file)));
    }

    @Test
    void deveProcessarMultiplosArquivos() throws IOException {
        MultipartFile f1 = mock(MultipartFile.class);
        MultipartFile f2 = mock(MultipartFile.class);
        when(f1.getBytes()).thenReturn(new byte[]{1});
        when(f2.getBytes()).thenReturn(new byte[]{2});
        when(cloudinary.uploader()).thenReturn(uploader);
        when(uploader.upload(any(byte[].class), any(Map.class)))
                .thenReturn(Map.of("secure_url", "https://cloudinary.com/1.jpg"))
                .thenReturn(Map.of("secure_url", "https://cloudinary.com/2.jpg"));

        List<String> urls = cloudinaryService.uploadImagens(List.of(f1, f2));

        assertEquals(2, urls.size());
    }
}