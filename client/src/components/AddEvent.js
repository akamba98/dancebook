import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import { Container, Paper, Button, Typography} from "@material-ui/core";
import DateTimePicker from "@material-ui/lab/DateTimePicker";
import LocalizationProvider from "@material-ui/lab/LocalizationProvider";
import { makeStyles } from "@material-ui/styles";
import AdapterDateFns from "@material-ui/lab/AdapterDateFns";
import { green, red } from "@mui/material/colors";

const useStyles = makeStyles(() => ({
	root: {
	  "& > *": {
		margin: "10px",
	  }
	}
  }));

 function AddEvent() {
    const paperStyle={padding:"50px 20px", width:600,margin:"20px auto"};
    const[name,setName]=useState("");
    const[location,setLocation]=useState("");
    const[description,setDescription]=useState("");
    const[startDateTime,setStartDateTime]=useState(new Date());
    const[endDateTime,setEndDateTime]=useState(new Date());
    const[response,setResponse]=useState([]);
    const startAt="";
    const endAt="";
    const classes = useStyles();

    const handleClick=(e)=>{
        e.preventDefault()
        var event={name,location,description,startAt,endAt};
        event.startAt=startDateTime.toISOString().split(".")[0];
        event.endAt=endDateTime.toISOString().split(".")[0];

        fetch("http://localhost:8080/event",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(event)
            })
            .then(res=> res.json())
            .then(result => {
            if(result.code!==200){
                setResponse(result);
                throw Error(result.message);
            }
            setResponse(result);
            setName("");
            setLocation("");
            setDescription("");
            setStartDateTime(new Date());
            setEndDateTime(new Date());
        })
        .catch(err => console.log(err))
    }

  return (
    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1 style={{color:"blue"}}>Add Event</h1>
            <form autoComplete="off">
                <TextField className={classes.root} id="outlined-basic" label="Name" variant="outlined" fullWidth 
                value={name}
                onChange={(e)=>setName(e.target.value.trimLeft())}
                />
                <TextField className={classes.root} id="outlined-basic" label="Location" variant="outlined" fullWidth 
                value={location}
                onChange={(e)=>setLocation(e.target.value.trimLeft())}
                />
                <TextField className={classes.root} id="outlined-basic" label="Description" variant="outlined" fullWidth
                value={description}
                onChange={(e)=>setDescription(e.target.value.trimLeft())}
                />
                <LocalizationProvider className={classes.root} dateAdapter={AdapterDateFns}>
                 <DateTimePicker 
                  label="Date&Time picker"
                  value={startDateTime}
                  onChange={setStartDateTime}
                  renderInput={(params) => <TextField {...params} />}
                />
                <DateTimePicker 
                    label="Date&Time picker"
                    value={endDateTime}
                    onChange={setEndDateTime}
                    renderInput={(params) => <TextField {...params} />}
                />
                </LocalizationProvider>
                <Button className={classes.root} variant="contained" color="secondary" onClick={handleClick}
                    disabled={!name || !location || !description}>
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

export default AddEvent;