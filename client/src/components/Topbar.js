import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Link from "@mui/material/Link";
import IconButton from "@mui/material/IconButton";
import LogoutIcon from "@mui/icons-material/Logout";
import { useNavigate } from "react-router-dom";
import { ReactSession } from "react-client-session";

 function Topbar() {
  const history = useNavigate();
  ReactSession.setStoreType("localStorage");

  const handleClick = (event)=>{
    event.preventDefault();
    ReactSession.remove("user");
    history("/user/login");
  }

  return (
    <div>
      <AppBar position="static">
        <Toolbar> 
          <Link style={{color:"white", marginRight:"8px"}} href="/"> Home </Link>     
          { ReactSession.get("user") ?  
            <>
              <Link style={{color:"white", marginRight:"8px"}} href="/event"> Events </Link>    
              <Link style={{color:"white", marginRight:"8px"}} href="/event/new"> Add Event </Link>
              <IconButton style={{color:"white", marginRight:"8px"}} onClick={handleClick}> <LogoutIcon/> </IconButton>
            </>
            : 
            <>  
            <Link style={{color:"white", marginRight:"8px"}} href="/user/login"> Login </Link>
            <Link style={{color:"white", marginRight:"8px"}} href="/user/register"> Register </Link>    
            </>
          }  
             
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default Topbar;