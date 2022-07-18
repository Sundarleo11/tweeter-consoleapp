var validate= function(password1,password2){ 

if(password1.length<6 || password1.length>9 || password2.length<6 || password2.length>9){

print('Password length should be between 6 to 9');
return false;
}
if(password1 === password2 ) { 
    return true;
}
else {
   print('Passwords did not match!...');
return false;
}
}; 
 