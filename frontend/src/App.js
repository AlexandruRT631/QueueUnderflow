import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from "./Home";
import User from "./Users/User";
import Question from "./Questions/Question";
import Answer from "./Questions/Answer";
import {createTheme} from "@mui/material/styles";

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
    return (
        <Router>
            <Routes>
                <Route path='/' element={<Home />}/>
                <Route path='/users/:id' element={<User theme={theme}/>}/>
                <Route path='/questions/:id' element={<Question theme={theme}/>}/>
                <Route path='/answers/:id' element={<Answer theme={theme}/>}/>
            </Routes>
        </Router>
    )
}

export default App