package model;

import com.test.addgroup;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.test.addgroup;
@Data
@ToString
public class User {

    @NotNull(message = "id不能为空", groups = {addgroup.class})
    private Integer id;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @Max(value = 100, message = "年龄不能超过100")
    @Min(value = 1, message = "年龄不能小于1")
    @NotNull(message = "年龄不能为空哦")
    private Integer age;

}
