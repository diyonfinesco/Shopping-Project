package com.example.demo.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategory() {
            return this.category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getPrice() {
            return this.price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
}
