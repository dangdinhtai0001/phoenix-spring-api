/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 6:04 PM
 */

package com.phoenix.api.model.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Token {
    private String accessToken;
    private long expiresIn;
    private String refreshToken;
    private String tokenType;
    private String idToken;
    private String sessionState;

}
