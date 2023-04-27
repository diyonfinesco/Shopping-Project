package com.example.demo.dto.product;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class UpdateProductDTO {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Size(max = 15)
    private String category;

    @NotNull
    @Min(1)
    private int price;

    @NotNull
    @Min(1)
    private int quantity;

    @Nullable
    private MultipartFile image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Nullable
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(@Nullable MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UpdateProductDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", image=" + image +
                '}';
    }
}