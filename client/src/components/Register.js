import React, { useState } from "react";
import { makeStyles } from "@material-ui/styles";
import TextField from "@material-ui/core/TextField";
import { Container ,Paper, Button, Typography} from "@material-ui/core";
import { green, red } from "@mui/material/colors";

const useStyles = makeStyles(() => ({
	root: {
	  "& > *": {
		margin: "10px",
	   
	  }
	}
  }));
 
function Register() {
	const paperStyle={padding:"50px 20px", width:600,margin:"20px auto"};
	const[username,setUsername]=useState("");
	const[firstName,setFirstName]=useState("");
	const[lastName,setLastName]=useState("");
	const[password,setPassword]=useState("");
	const[clubName,setClubName]=useState("");
	const[response,setResponse]=useState([]);
	const isDancer = window.location.href.split("/")[5]==="dancer" ? "true" : "false";
	const classes = useStyles();

	const handleClick=(event)=>{
		event.preventDefault()
		const user={username,password,firstName,lastName,clubName};

		fetch(`http://localhost:8080/user/register?dancer=${isDancer}`,{
			method:"POST",
			headers:{"Content-Type":"application/json"},
			body:JSON.stringify(user)
		})
		.then(res => res.json())
		.then((result) => {
			if(result.code!==200){
				setResponse(result);
				throw Error(result.message);
			}
			else {
				setUsername("");
				setPassword("");
				setFirstName("");
				setLastName("");
				setClubName("");
				setResponse(result);
			}			
		})
		.catch(err=>{console.log(err)});
	}
	return (
		<Container>
			<Paper elevation={3} style={paperStyle}>
				<h1 style={{color:"blue"}}>Register</h1>
				<form className={classes.root} noValidate autoComplete="off">
					<TextField className={classes.root} label="Username" variant="outlined" fullWidth 
					value={username}
					onChange={(e)=>setUsername(e.target.value.trimLeft())}
					/>
					<TextField className={classes.root} label="Password" variant="outlined" fullWidth type="password"
					value={password}
					onChange={(e)=>setPassword(e.target.value.trimLeft())}
					/>
					{ isDancer==="true" ?
					<>
						<TextField className={classes.root} label="First Name" variant="outlined" fullWidth
						value={firstName}
						onChange={(e)=>setFirstName(e.target.value.trimLeft())}
						/>
						<TextField className={classes.root} label="Last Name" variant="outlined" fullWidth
						value={lastName}
						onChange={(e)=>setLastName(e.target.value.trimLeft())}
						/>
					</>
					: <></>
					}
					<TextField className={classes.root} label="Club Name" variant="outlined" fullWidth 
					value={clubName}
					onChange={(e)=>setClubName(e.target.value.trimLeft())}
					/>
					<Button variant="contained" color="secondary" onClick={handleClick} 
					disabled={isDancer==="true" ? 
            !clubName || !username || !password || !firstName || !lastName :
						!clubName || !username || !password 
					 
					}>
						Submit
					</Button>
					{response.code===200 ? 
						<Typography sx={{ color: green[500] }}> {response.message} </Typography> :
						<Typography sx={{ color: red[500] }}> {response.message} </Typography>
					}
				</form>
			</Paper>
		</Container>
	);
}

export default Register;