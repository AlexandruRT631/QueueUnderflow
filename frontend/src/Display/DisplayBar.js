import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import {TextField, ThemeProvider} from "@mui/material";
import Cookies from "js-cookie";
import {useNavigate} from "react-router-dom";

const DisplayBar = (props) => {
    const navigate = useNavigate();
    const logout = () => {
        Cookies.remove('token')
        Cookies.remove('moderator')
        window.location.reload()
    }
    const [search, setSearch] = React.useState('')

    return (
        <ThemeProvider theme={props.theme}>
            <Box sx={{flexGrow: 1}}>
                <AppBar position="static">
                    <Toolbar>
                        <Button color="inherit" onClick={() => navigate('/')}>Home</Button>
                        {!props.token && <Button color="inherit" onClick={() => navigate('/login')}>Login</Button>}
                        {!props.token &&
                            <Button color="inherit" onClick={() => navigate('/register')}>Register</Button>}
                        {props.token && <Button color="inherit" onClick={logout}>Logout</Button>}
                        <TextField id="search" label="Search" variant="outlined"
                                   sx={{m: 1, alignSelf: 'right'}}
                                   onChange={(e) => setSearch(e.target.value)}>
                            {search}
                        </TextField>
                        <Button color="inherit" onClick={() => {
                            navigate(`/search/${search}`)
                            window.location.reload()
                        }}>Search</Button>
                    </Toolbar>
                </AppBar>
            </Box>
        </ThemeProvider>
    )
}

export default DisplayBar;