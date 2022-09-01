package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter

public class MemberForm {

    @NotEmpty//name을 채우지 않으면 오류가 나는 annotation
    private String name;

    private String city;
    private String street;
    private String zipcode;

}
