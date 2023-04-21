import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from "./Home";
import User from "./User";
import Question from "./Question";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path='/' element={<Home />}/>
                <Route path='/users/:id' element={<User />}/>
                <Route path='/questions/:id' element={<Question />}/>
            </Routes>
        </Router>
    )
}

export default App