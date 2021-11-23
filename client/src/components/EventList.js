import React, { useState, useEffect } from "react";
import Paper from "@material-ui/core/Paper";
import { Typography } from "@material-ui/core";
import { Link as RouterLink } from "react-router-dom";
import Link from "@material-ui/core/Link";
import { ReactSession } from "react-client-session";


function EventList(){
	const paperStyle={padding:"50px 20px", width:600,margin:"20px auto"};
    const[events,setEvents]=useState([]);
	ReactSession.setStoreType("localStorage");
    useEffect(()=>{
		fetch(`http://localhost:8080/event/old`,{
			method: "DELETE"
		});
		
		fetch("http://localhost:8080/event",{
			method: "GET"
		})
		.then(res=>res.json())
		.then((result)=>{
			setEvents(result);
		}
		)
	},[])
    return(
        <Paper elevation={3} style={paperStyle}>
				<Typography textAlign="center" fontSize="large"> Events </Typography>
				{events.map(event=>(
					<Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={event.eventId}>
					<Link underline="none" component={RouterLink} to={`/event/${event.eventId}`}>
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
							<Typography>
								Interested:{event.interestCount}
							</Typography>
							</Link>				          
					</Paper>
					))
				}
			</Paper>
    )
}

export default EventList;