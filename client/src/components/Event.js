import React, { useState, useEffect, useRef, useLayoutEffect } from "react";
import Paper from "@material-ui/core/Paper";
import { Typography } from "@material-ui/core";
import StarBorderRoundedIcon from "@mui/icons-material/StarBorderRounded";
import IconButton from "@mui/material/IconButton";
import StarRateRoundedIcon from "@mui/icons-material/StarRateRounded";
import Switch from "@mui/material/Switch";
import Collapse from "@mui/material/Collapse";
import FormControlLabel from "@mui/material/FormControlLabel";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { ReactSession } from "react-client-session";
import { Navigate } from "react-router";

function Event(){
	const paperStyle={padding:"50px 20px", width:600,margin:"20px auto"};
    const[event,setEvent]=useState([]);
    const [isLiked,setIsLiked]=useState(false);
    const [interestCount,setInterestCount]=useState(0);
    const [comments,setComments]=useState([]);
    const [checked, setChecked] = useState(false);
    const [text, setText] = useState("");
    const [isLoaded, setIsLoaded]=useState(false);
    ReactSession.setStoreType("localStorage");
    const eventId = window.location.href.split("/")[4];
    const userId = ReactSession.get("user").userId;
    const interestId={userId,eventId};
    const interest = {interestId};
    const comment = {userId,text,eventId};

    const handleChange = () => {
        setChecked((prev) => !prev);
    };

    const firstUpdate = useRef(true);
        useLayoutEffect(() => {
            if (firstUpdate.current) {
                firstUpdate.current = false;
            return;
        }
    });

    useEffect(()=>{
		fetch(`http://localhost:8080/interest/${userId}/${eventId}`,{
			method: "GET"
		})
		.then(res=>res.json())
		.then((result)=>{
			setIsLiked(result);
		}
		)
	})

    const handleClick=(event)=>{
        event.preventDefault()
        fetch("http://localhost:8080/interest",{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(interest)
        })
        setIsLiked(!isLiked);
        isLiked ? setInterestCount(interestCount+1) : setInterestCount(interestCount-1);
    }

    const postComment=(event)=>{
        event.preventDefault()
        fetch("http://localhost:8080/user/comment",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(comment)
        })
        .then(res=>res.json())
		    .then((result)=>{
          setComments(result);
		}
		)
        setText("");
    }

    useEffect(()=>{
		fetch(`http://localhost:8080/event/${eventId}`,{
			method: "GET"
		})
		.then(res=>res.json())
		.then((result)=>{
      if(result){
        setEvent(result);
        setInterestCount(result.interestCount);
        setIsLoaded(true);
      }
      console.log(result.eventId);
		}
		)
	},[interestCount,eventId,comments,isLoaded])

    return(
        <>
        {
          
        isLoaded && 
        <>
        { !event.name ?
            <Navigate to="/event"/> : 
            <Paper elevation={3} style={paperStyle}>
            <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}>
                <Typography>
                    Name:{event.name}
                </Typography>
                <Typography>
                    Location:{event.location}
                </Typography>
                <Typography>
                    Description:{event.description}
                </Typography>
                <Typography>
                    Start At:{new Date(event.startAt).toUTCString()}
                </Typography>
                <Typography>
                    End At:{new Date(event.endAt).toUTCString()}
                </Typography>
                { isLiked===true ?
                    (<IconButton onClick={handleClick}>
                        <StarRateRoundedIcon/>
                    </IconButton>)	 : 
                    (<IconButton onClick={handleClick}>
                        <StarBorderRoundedIcon/>
                    </IconButton>)                	
                }  <span>{interestCount}</span>
                <FormControlLabel style={{margin:"10px",padding:"15px", textAlign:"left"}}
                    control={<Switch checked={checked} onChange={handleChange} />}
                    label="Show Comments"
                />
                <Collapse in={checked}>
                    <form noValidate autoComplete="off">
                        <TextField id="outlined-basic" label="Comment" variant="outlined" fullWidth 
                            value={text}
                            onChange={(e)=>setText(e.target.value.trimLeft())}
                        />
                        <Button variant="contained" color="secondary" onClick={postComment} disabled={!text}>
                            Submit
                        </Button>
                    </form>
                    {event.comments ? event.comments.sort((l,r)=> -(l.createdAt - r.createdAt)).map(element=>(
                        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={element.commentId}>
                            <Typography>
                            {element.user.firstName ? 
                                `${element.user.firstName} ${element.user.lastName}` :
                                `${element.user.clubName}`
                            }
                            </Typography>
                            <Typography>
                                {element.text}
                            </Typography>	          
                        </Paper>
                        )) : <div/>
                    }
                </Collapse>        
            </Paper> 
            </Paper> 
            }
            </>
        }
        </>
    )
}

export default Event;