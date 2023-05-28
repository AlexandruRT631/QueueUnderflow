import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import axios from "axios";
import DisplayBar from "../Display/DisplayBar";
import {Box, Button, Container, TextField, ThemeProvider, Typography} from "@mui/material";

const NewQuestion = (props) => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        title: null,
        userId: null,
        content: null,
        picture: null,
        tags: null
    });
    const [error, setError] = useState("");

    const onClick = () => {
        if (formData.title !== null && formData.content !== null && formData.title !== "" && formData.content !== "") {
            axios.post('http://localhost:8080/questions/insertQuestion', {...formData, userId: props.token, tags: toTagsList(formData.tags)})
                .then((res) => {
                    if (res.status === 200) {
                        navigate(`/questions/${res.data.questionId}`);
                    }
                })
                .catch((error) => {
                    if (error.response && error.response.status === 401) {
                        console.log("Unauthorized: Incorrect email or password");
                        setError("Incorrect email or password");
                    } else {
                        console.log("An error occurred:", error);
                    }
                });
        }
        else {
            setError("Title and Content are required");
        }
    }

    const toTagsList = (str) => {
        if (!str) {
            return []; // Return an empty list if the string is null or empty
        }

        return str.split(',').map(item => item.trim());
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
                            (<>
                                <Typography variant={'h4'}
                                            sx={{
                                                p: 1,
                                                color: 'primary.contrastText',
                                                alignSelf: 'center'
                                            }}>New Question</Typography>
                                <TextField id="title" label="Title" variant="outlined" required
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, title: e.target.value})}/>
                                <TextField id="content" label="Content" variant="outlined" required multiline
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, content: e.target.value})}/>
                                <TextField id="picture" label="Picture Link" variant="outlined"
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, picture: e.target.value})}/>
                                <TextField id="tags" label="Tags (Comma separated)" variant="outlined"
                                           sx={{m: 1, alignSelf: 'center'}}
                                           onChange={(e) => setFormData({...formData, tags: e.target.value})}/>
                                {error && <Typography sx={{color: 'error.main', alignSelf: 'center'}}>{error}</Typography>}
                                <Button variant="contained" size="medium" sx={{m: 1, width: '10%', alignSelf: 'center'}}
                                        onClick={onClick}>Submit</Button>
                            </>) : (
                                <Typography variant={'h4'} sx={{
                                    p: 1,
                                    color: 'primary.contrastText',
                                    alignSelf: 'center'
                                }}>Need to be logged in</Typography>
                            )}
                    </Box>
                </ThemeProvider>
            </Container>
        </>
    );
}

export default NewQuestion