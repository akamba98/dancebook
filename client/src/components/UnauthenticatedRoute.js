import { Navigate } from "react-router-dom";
import { ReactSession } from "react-client-session";

 function UnauthenticatedRoute({component: Component}) {
  ReactSession.setStoreType("localStorage");
  return(
    <>
    { !ReactSession.get("user")? 
      <Component/> :
      <Navigate to="/event"/>
    }
    </>
  );
}
export default UnauthenticatedRoute;