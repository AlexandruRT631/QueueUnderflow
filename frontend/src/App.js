import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from "./Pages/Home";
import User from "./Pages/User";
import Question from "./Pages/Question";
import Answer from "./Pages/Answer";
import {createTheme} from "@mui/material/styles";
import Tag from "./Pages/Tag";
import Login from "./Pages/Login";
import Cookies from "js-cookie";
import Register from "./Pages/Register";
import NewQuestion from "./Pages/NewQuestion";
import Search from "./Pages/Search";

const theme = createTheme({
    palette: {
        primary: {
            light: '#6fbf73',
            main: '#4caf50',
            dark: '#357a38',
            contrastText: '#000',
        },
        secondary: {
            light: '#f1f1f1',
            main: '#eeeeee',
            dark: '#c6c6c6',
            contrastText: '#000',
        },
    },
});

const App = () => {
    const token = Cookies.get('token')

    return (
        <Router>
            <Routes>
                <Route path='/' element={<Home theme={theme} token={token}/>}/>
                <Route path='/users/:id' element={<User theme={theme} token={token}/>}/>
                <Route path='/questions/:id' element={<Question theme={theme} token={token}/>}/>
                <Route path='/answers/:id' element={<Answer theme={theme} token={token}/>}/>
                <Route path='/tag/:tag' element={<Tag theme={theme} token={token}/>}/>
                <Route path='/login' element={<Login theme={theme} token={token}/>}/>
                <Route path='/register' element={<Register theme={theme} token={token}/>}/>
                <Route path='/newQuestion' element={<NewQuestion theme={theme} token={token}/>}/>
                <Route path='/search/:search' element={<Search theme={theme} token={token}/>}/>
            </Routes>
        </Router>
    )
}

export default App