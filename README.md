# meAjudaaAjudar
Me ajuda a ajudar is a web application that gather data regarding serious charity institution in Brazil (curerently only in Rio grande do sul) and therefore helps the user to find an institution to donate to which aligns with one's beliefs (full description below)

Available at http://meajudaaajudar.com.br/ (Available only in Portuguse due to the local scope of the project)

![system working](https://github.com/sylleryum/meAjudaaAjudar/blob/main/demo.gif)

This readme is for both back and front end, the REPO/source code for the frontend can be found here: https://github.com/sylleryum/meajudaaajudarfrontend

## Why this is useful

* this was created initially when I was trying to find a way to donate money, however, upon trying to identify to whom I'd donate, I realized how hard it is to find an institution if you don't know it beforehand
* **This web application then tries to help with this "gap", to when one needs help to find a charity institution to donate to**


## Full description/features

* This API collects data manually for charity institution that look 100% serious/committed to its cause and makes it easily available to everyone
* Provides location (city) and causes filters to help one choose
* Provides several details regarding the institutions as description, website (when available), logo, location and several others
* All the institutions listed have clear and easy details on how to donate, normally the donation page of the institution and/or full details to transfer money
* for read-only or write access, an access token is required, to obtain the same username and password should be sent to the end point /login, if successfully authenticated, an access code will be provided depending on the role (read or write token)
* trace-id and error message is sent in case of error
