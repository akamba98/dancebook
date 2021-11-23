import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/styles";
import { Container, Paper, Button, Typography} from "@material-ui/core";
import { useNavigate } from "react-router-dom";
import { ReactSession } from "react-client-session";
import { red } from "@mui/material/colors";

const useStyles = makeStyles(() => ({
	root: {
	  "& > *": {
		margin: "10px",
	   
	  }
	}
  }));

function Login() {
    const paperStyle={padding:"50px 20px", width:600,margin:"20px auto"};
    const [username,setUsername]=useState("");
    const [password,setPassword]=useState("");
    const history = useNavigate();
	  const classes = useStyles();
    const [response, setResponse]=useState([]);
    ReactSession.setStoreType("localStorage");

    const handleClick = ()=>{
      const userInput={username,password};
      fetch("http://localhost:8080/user/login",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(userInput)
        })
        .then(res=>res.json())
        .then((result)=>{
          if(result.code){
            setResponse(result);
            throw Error(result.message);
          }
          else{
            ReactSession.set("user", result);
            setUsername("");
            setPassword("");
            history("/event")
          }    
        }
        )
        .catch(err => console.log(err))
    }

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
          <h1 style={{color:"blue"}}>Login</h1>
          <form noValidate autoComplete="off">
              <TextField className={classes.root} label="Username" variant="outlined" fullWidth 
              value={username}
              onChange={(e)=>setUsername(e.target.value.trimLeft())}
              />
              <TextField className={classes.root} label="Password" variant="outlined" fullWidth type="password"
              value={password}
              onChange={(e)=>setPassword(e.target.value.trimLeft())}
              />
              <Button variant="contained" color="secondary" onClick={()=>{handleClick()}} disabled={!username || !password}>
                  Submit
              </Button>
              {response.code && <Typography sx={{ color: red[500] }}> {response.message} </Typography>}
          </form>
      </Paper>
    </Container>
  );
}

export default Login;