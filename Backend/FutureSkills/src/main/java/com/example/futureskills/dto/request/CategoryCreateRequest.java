package com.example.futureskills.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CategoryCreateRequest {
    @Size(min=3, message = "CATEGORY_NAME_INVALID")
   private String name;
   private String description;
   private Set<String> subCategories;
}
