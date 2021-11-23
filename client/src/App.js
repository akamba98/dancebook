
import { BrowserRouter,Routes, Route, Navigate } from "react-router-dom";
import AddEvent from "./components/AddEvent";
import EventList from "./components/EventList";
import Login from "./components/Login";
import Register from "./components/Register"
import Topbar from "./components/Topbar";
import Event from "./components/Event";
import Home from "./components/Home";
import AuthenticatedRoute from "./components/AuthenticatedRoute";
import UnauthenticatedRoute from "./components/UnauthenticatedRoute";
import RegisterChoise from "./components/RegisterChoise";

function App() {
  return (
    <BrowserRouter>
    	<Topbar/>
		<Routes>
			<Route exact path="/" element={<Home/>}/>
			<Route exact path="/user/register/dancer" element={<UnauthenticatedRoute component={Register}/>}/>
			<Route exact path="/user/register/danceClub" element={<UnauthenticatedRoute component={Register}/>}/>
			<Route exact path="/user/register" element={<UnauthenticatedRoute component={RegisterChoise}/>}/>	
			<Route exact path="/user/login" element={<UnauthenticatedRoute component={Login}/>}/>
			<Route exact path="/event" element={<AuthenticatedRoute component={EventList}/>}/>
			<Route exact path="/event/new" element={<AuthenticatedRoute component={AddEvent}/>}/>
			<Route exact path="/event/:id" element={<AuthenticatedRoute component={Event}/>}/>
			<Route exact path="*" element={<Navigate to="/" />}/>			
		</Routes>
  	</BrowserRouter>
  );
}

export default App;

