import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import {ThemeProvider} from "@mui/material";
import Cookies from "js-cookie";
import {useNavigate} from "react-router-dom";

const DisplayBar = (props) => {
    const navigate = useNavigate();
    const logout = () => {
        Cookies.remove('token')
        window.location.reload()
    }

    return (
        <ThemeProvider theme={props.theme}>
            <Box sx={{flexGrow: 1}}>
                <AppBar position="static">
                    <Toolbar>
                        <Button color="inherit" onClick={() => navigate('/')}>Home</Button>
                        {!props.token && <Button color="inherit" onClick={() => navigate('/login')}>Login</Button>}
                        {!props.token && <Button color="inherit" onClick={() => navigate('/register')}>Register</Button>}
                        {props.token && <Button color="inherit" onClick={logout}>Logout</Button>}
                    </Toolbar>
                </AppBar>
            </Box>
        </ThemeProvider>
    )
}

export default DisplayBar;