import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import axios from "axios";
import Cookies from "js-cookie";
import DisplayBar from "../Display/DisplayBar";
import {Box, Button, Container, TextField, ThemeProvider, Typography} from "@mui/material";

const Register = (props) => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        eMail: null,
        password: null,
        firstName: null,
        lastName: null,
        phone: null,
        picture: null
    });
    const [error, setError] = useState("");

    const onClick = () => {
        console.log(formData)
        if (formData.eMail !== null && formData.password !== null && formData.firstName !== null && formData.lastName !== null) {
            if (formData.picture === null) {
                formData.picture = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"
            }
            axios.post('http://localhost:8080/register', formData)
                .then((res) => {
                    console.log(res);
                    if (res.status === 200) {
                        Cookies.set('token', res.data, {expires: 31});
                        navigate('/');
                        window.location.reload()
                    }
                })
                .catch((error) => {
                    if (error.response && error.response.status === 400) {
                        console.log("Email already in use");
                        setError("Email already in use")
                    } else {
                        console.log("An error occurred:", error);
                    }
                    Cookies.remove("token")
                });
        } else {
            setError("Please fill in all required fields")
        }
    }

    return (
        <>
            <DisplayBar theme={props.theme} token={props.token}/>
            <Container sx={{p: 1, textDecoration: 'none'}}>
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
                                }}>Already registered</Typography>
                            ) : (<>
                                <Typography variant={'h4'}
                                            sx={{
                                                p: 1,
                                                color: 'primary.contrastText',
                                                alignSelf: 'center'
                                            }}>Register</Typography>
                                <TextField id="email" label="E-Mail" variant="outlined" required
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, eMail: e.target.value})}/>
                                <TextField id="pasword" label="Password" variant="outlined" type='password' required
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, password: e.target.value})}/>
                                <TextField id="firstName" label="First Name" variant="outlined" required
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, firstName: e.target.value})}/>
                                <TextField id="lastName" label="Last Name" variant="outlined" required
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, lastName: e.target.value})}/>
                                <TextField id="phone" label="Phone" variant="outlined"
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, phone: e.target.value})}/>
                                <TextField id="picture" label="Picture Link" variant="outlined"
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, picture: e.target.value})}/>
                                {error && <Typography sx={{color: 'error.main', alignSelf: 'center'}}>{error}</Typography>}
                                <Button variant="contained" size="medium" sx={{m: 1, width: '10%', alignSelf: 'center'}}
                                        onClick={onClick}>Register</Button>
                            </>)}
                    </Box>
                </ThemeProvider>
            </Container>
        </>
    );
}

export default Register;