import React, {useState} from 'react';
import axios from "axios";
import Cookies from "js-cookie";
import {Box, Button, Container, Link, TextField, ThemeProvider, Typography} from "@mui/material";
import DisplayBar from "../Display/DisplayBar";
import {useNavigate} from "react-router-dom";

const Login = (props) => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({eMail: '', password: ''});
    const [error, setError] = useState("");

    const onClick = () => {
        axios.post('http://localhost:8080/login', formData)
            .then((res) => {
                console.log(res);
                if (res.status === 200) {
                    Cookies.set('token', res.data, {expires: 31});
                    navigate('/');
                    window.location.reload()
                }
            })
            .catch((error) => {
                if (error.response && error.response.status === 401) {
                    console.log("Unauthorized: Incorrect email or password");
                    setError("Incorrect email or password");
                } else {
                    console.log("An error occurred:", error);
                }
                Cookies.remove("token")
            });

    }

    return (
        <>
            <DisplayBar theme={props.theme} token={props.token}/>
            <Container sx={{p: 1, textDecoration: 'none'}} component={Link} href={props.href}>
                <ThemeProvider theme={props.theme}>
                    <Box sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        border: 10,
                        borderColor: `primary.light`,
                        p: 2,
                        bgcolor: `${props.bgColor}`
                    }}>
                        {props.token ?
                            (
                                <Typography variant={'h4'} sx={{
                                    p: 1,
                                    color: 'primary.contrastText',
                                    alignSelf: 'center'
                                }}>Already logged in</Typography>
                            ) : (<>
                                <Typography variant={'h4'}
                                            sx={{
                                                p: 1,
                                                color: 'primary.contrastText',
                                                alignSelf: 'center'
                                            }}>Login</Typography>
                                <TextField id="email" label="E-Mail" variant="outlined"
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, eMail: e.target.value})}/>
                                <TextField id="password" label="Password" variant="outlined" type='password'
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, password: e.target.value})}/>
                                {error && <Typography sx={{color: 'error.main', alignSelf: 'center'}}>{error}</Typography>}
                                <Button variant="contained" size="medium" sx={{m: 1, width: '10%', alignSelf: 'center'}}
                                        onClick={onClick}>Login</Button>
                            </>)}
                    </Box>
                </ThemeProvider>
            </Container>
        </>
    );
};

export default Login;
