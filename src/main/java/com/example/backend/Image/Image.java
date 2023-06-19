package com.example.backend.Image;


import com.example.backend.ImageAnother.ImageAnother;
import com.example.backend.Products.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "type")
	private String type;

	@Column(name = "image", unique = false, nullable = false, length = 100000)
	private byte[] image;

	@JsonIgnore
	@OneToOne(mappedBy = "image")
	private Product product;

	@JsonIgnore
	@OneToMany(mappedBy = "imageMain", cascade = CascadeType.ALL)
	private List<ImageAnother> ImageAnother;
}
