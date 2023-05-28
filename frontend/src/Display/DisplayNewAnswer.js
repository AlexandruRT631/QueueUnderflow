import {Box, Button, Container, TextField, ThemeProvider, Typography} from "@mui/material";
import React, {useState} from "react";
import axios from "axios";

const DisplayNewAnswer = (props) => {
    const [formData, setFormData] = useState({
        questionId: props.questionId,
        userId: props.token,
        content: null,
        picture: null,
    });
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const onClick = () => {
        if (formData.content !== null && formData.content !== "") {
            //console.log(formData);
            axios.post('http://localhost:8080/answers/insertAnswer', formData)
                .then((res) => {
                    if (res.status === 200) {
                        setSuccess("Successfully created answer");
                        setError("");
                    }
                    //console.log(res.data);
                })
                .catch((error) => {
                    console.log("An error occurred:", error);
                });
        } else {
            setError("Content is required");
            setSuccess("");
        }
    }

    return (
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
                    <TextField id="content" label="Content" variant="outlined" required multiline
                               defaultValue={formData.content}
                               sx={{m: 1, alignSelf: 'center'}}
                               onChange={(e) => setFormData({...formData, content: e.target.value})}/>
                    <TextField id="picture" label="Picture Link" variant="outlined"
                               defaultValue={formData.picture}
                               sx={{m: 1, alignSelf: 'center'}}
                               onChange={(e) => setFormData({...formData, picture: e.target.value})}/>
                    {error &&
                        <Typography sx={{color: 'error.main', alignSelf: 'center'}}>{error}</Typography>}
                    {success &&
                        <Typography
                            sx={{color: 'success.main', alignSelf: 'center'}}>{success}</Typography>}
                    <Button variant="contained" size="medium" sx={{m: 1, width: '10%', alignSelf: 'center'}}
                            onClick={onClick}>Submit</Button>
                </Box>
            </ThemeProvider>
        </Container>
    )
}

export default DisplayNewAnswer;