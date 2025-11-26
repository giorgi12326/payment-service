package org.example.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paymentservice.entity.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullUserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
