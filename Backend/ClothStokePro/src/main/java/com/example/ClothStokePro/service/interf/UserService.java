<<<<<<< HEAD
package com.example.ClothStokePro.service.interf;

import com.example.ClothStokePro.dto.LoginRequestDto;
import com.example.ClothStokePro.dto.Response;
import com.example.ClothStokePro.dto.UserDto;
import com.example.ClothStokePro.entity.User;

public interface UserService {
    Response registerUser(UserDto registrationRequest);

    Response loginUser(LoginRequestDto loginRequest);

    Response getAllUsers();

    User getLoginUser();

    Response getUserInfoAndOrderHistory();
=======
package com.example.ClothStokePro.service.interf; 
 
import com.example.ClothStokePro.dto.LoginRequestDto; 
import com.example.ClothStokePro.dto.Response; 
import com.example.ClothStokePro.dto.UserDto; 
import com.example.ClothStokePro.entity.User; 
 
public interface UserService { 
Response registerUser(UserDto registrationRequest); 
Response loginUser(LoginRequestDto loginRequest); 
Response getAllUsers(); 
User getLoginUser(); 
Response getUserInfoAndOrderHistory(); 
>>>>>>> 1471cd84526c5175f20111285974b14044415d30
}
