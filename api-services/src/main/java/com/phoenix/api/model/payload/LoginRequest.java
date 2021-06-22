/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 6:02 PM
 */

package com.phoenix.api.model.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
