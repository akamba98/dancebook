import { Navigate } from "react-router-dom";
import { ReactSession } from "react-client-session";

 function AuthenticatedRoute({component: Component}) {
  ReactSession.setStoreType("localStorage");
  return(
    <>
    {ReactSession.get("user")? 
      <Component/> :
      <Navigate to="/user/login"/>
    }
    </>
  );
}
export default AuthenticatedRoute;