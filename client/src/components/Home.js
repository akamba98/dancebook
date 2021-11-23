import { Link, Typography } from "@material-ui/core";
import { ReactSession } from "react-client-session";

function Home(){
    ReactSession.setStoreType("localStorage");

    return (
        <>
        {ReactSession.get("user") ? 
            <Typography>
                Hello {ReactSession.get("user").firstName ? 
                `${ReactSession.get("user").firstName} ${ReactSession.get("user").firstName}`
                : ReactSession.get("user").clubName
                }
            </Typography> :
            <Typography>
                Hello please <Link href="/user/login"> login</Link>
            </Typography>        
        }
        </>
    );
}

export default Home;