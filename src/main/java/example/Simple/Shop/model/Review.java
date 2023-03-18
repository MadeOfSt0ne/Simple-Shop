package example.Simple.Shop.model;

import example.Simple.Shop.model.user.dto.UserShortDto;

import java.time.LocalDate;

public class Review {

    private long id;
    private long productId;
    private UserShortDto userDto;
    private LocalDate date;
    private String text;
}
