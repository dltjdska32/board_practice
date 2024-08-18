package practice.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_USER("user");

    private String value;
}
