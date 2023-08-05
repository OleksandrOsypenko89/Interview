package com.osypenko.model.interview;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "images")
@ToString
public class ImageID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "image_1")
    private String image1;
    @Column(name = "image_2")
    private String image2;
    @Column(name = "image_3")
    private String image3;
}
