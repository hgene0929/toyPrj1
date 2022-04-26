package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.") //필수로 받을 정보를 위한 어노테이션
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
