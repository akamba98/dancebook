import { Typography } from "@material-ui/core";
import { Link as RouterLink } from "react-router-dom";
import Link from "@material-ui/core/Link";
import Paper from "@material-ui/core/Paper";

function RegisterChoise(){
    const paperStyle={padding:"50px 20px", width:600,margin:"20px auto"};
    return ( 
    <div>
        <Typography>
            Choose registration
        </Typography>
        <Link underline="none" component={RouterLink} to={`/user/register/dancer`}>
        <Paper elevation={3} style={paperStyle}>
            Dancer
        </Paper>
        </Link>
        <Link underline="none" component={RouterLink} to={`/user/register/danceClub`}>
        <Paper elevation={3} style={paperStyle}>
           Dance club 
        </Paper>
        </Link>
    </div>
    );
}

export default RegisterChoise;