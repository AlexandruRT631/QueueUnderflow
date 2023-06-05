import {Avatar, Box, Button, Container, IconButton, Link, TextField, ThemeProvider, Typography} from "@mui/material";
import Image from "mui-image";
import EditIcon from "@mui/icons-material/Edit";
import React, {useState} from "react";
import axios from "axios";
import DeleteIcon from "@mui/icons-material/Delete";

const DisplayAnswer = (props) => {
    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState({
        answerId: props.answerId,
        questionId: props.questionId,
        content: props.content,
        picture: props.picture,
        votes: props.votes,
    });
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const onClickUpdate = () => {
        if (formData.content !== null && formData.content !== "") {
            //console.log(formData);
            axios.put('http://localhost:8080/answers/updateAnswer', formData)
                .then((res) => {
                    if (res.status === 200) {
                        setSuccess("Successfully updated answer");
                        setError("");
                        setIsEditing(false);
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

    const onClickDelete = () => {
        axios.delete(`http://localhost:8080/answers/deleteById/${props.answerId}`)
            .then((res) => {
                if (res.status === 200) {
                    setSuccess("Successfully deleted answer");
                    setError("");
                }
                //console.log(res.data);
            })
            .catch((error) => {
                console.log("An error occurred:", error);
            });
    }

    const getVote = () => {
        if (props.token == null) {
            return null;
        }
        const vote = props.votes.find(value => value.userId === parseInt(props.token));
        //console.log(vote)
        return vote ? vote.positiveVote : null;
    }

    const onClickUpvote = () => {
        if (props.token === undefined || props.userId === parseInt(props.token)) {
            return;
        }
        //console.log(formData);
        axios.put('http://localhost:8080/answers/updateAnswer', ((getVote() !== null && getVote() === true) ?
            ({
                ...formData,
                votes: formData.votes.filter(value => value.userId !== parseInt(props.token))
            }) :
            ({
                ...formData,
                votes: formData.votes.filter(value => value.userId !== parseInt(props.token)).concat({
                    userId: parseInt(props.token),
                    positiveVote: true
                })

            })))
            .then((res) => {
                if (res.status === 200) {
                    //console.log("Successful upvote")
                    setFormData({...formData, votes: res.data.votes})
                }
                //console.log(res.data);
            })
            .catch((error) => {
                console.log("An error occurred:", error);
            });
    }

    const onClickDownvote = () => {
        if (props.token === undefined || props.userId === parseInt(props.token)) {
            return;
        }
        // console.log(formData);
        axios.put('http://localhost:8080/answers/updateAnswer', ((getVote() !== null && getVote() === false) ?
            ({
                ...formData,
                votes: formData.votes.filter(value => value.userId !== parseInt(props.token))
            }) :
            ({
                ...formData,
                votes: formData.votes.filter(value => value.userId !== parseInt(props.token)).concat({
                    userId: parseInt(props.token),
                    positiveVote: false
                })

            })))
            .then((res) => {
                if (res.status === 200) {
                    //console.log("Successful downvote")
                    setFormData({...formData, votes: res.data.votes})
                }
                //console.log(res.data);
            })
            .catch((error) => {
                console.log("An error occurred:", error);
            });
    }

    return (
        <Container sx={{p: 1}}>
            <ThemeProvider theme={props.theme}>
                <Box sx={{display: 'flex', flexDirection: 'row', border: 10, borderColor: 'primary.main', p: 0}}>
                    <Box sx={{
                        width: '20%',
                        bgcolor: 'secondary.dark',
                        textAlign: 'center',
                        display: 'flex',
                        flexDirection: 'column',
                        p: 2
                    }}>
                        <Box sx={{display: 'flex', flexDirection: 'column', textDecoration: 'none'}} component={Link}
                             href={`/users/${props.userId}`}>
                            <Avatar src={props.userPicture} alt={'unavailable'}
                                    sx={{width: '60%', height: 'auto', alignSelf: 'center', p: 2}} variant='square'/>
                            <Typography sx={{
                                alignSelf: 'center',
                                color: 'primary.contrastText'
                            }}>{props.userFirstName}</Typography>
                            <Typography sx={{
                                alignSelf: 'center',
                                color: 'primary.contrastText'
                            }}>{props.userLastName}</Typography>
                            <Typography sx={{
                                alignSelf: 'center',
                                color: 'primary.contrastText'
                            }}>User score: {props.userScore}</Typography>
                        </Box>
                        <Button sx={{width: '20%', alignSelf: 'center'}} onClick={onClickUpvote}>
                            <Image
                                src={(getVote() !== null && getVote() === true) ? (
                                    'https://styles.redditmedia.com/t5_2qnty/styles/postUpvoteIconActive_lritbcc3d6x11.png'
                                ) : (
                                    'https://styles.redditmedia.com/t5_2qnty/styles/postUpvoteIconInactive_n5ydt0uuj6x11.png'
                                )}
                                duration={0}/>
                        </Button>
                        <Typography>Vote: {props.vote}</Typography>
                        <Button sx={{width: '20%', alignSelf: 'center'}} onClick={onClickDownvote}><Image
                            src={(getVote() !== null && getVote() === false) ? (
                                'https://styles.redditmedia.com/t5_2qnty/styles/postDownvoteIconActive_mqbieia4d6x11.png'
                            ) : (
                                'https://styles.redditmedia.com/t5_2qnty/styles/postDownvoteIconInactive_cnbj1c0wj6x11.png'
                            )}
                            duration={0}/>
                        </Button>
                    </Box>
                    <Box sx={{width: '80%', bgcolor: 'secondary.main', p: 2}}>
                        {isEditing ? (
                            <>
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
                                <Button variant="contained" size="medium" sx={{m: 1, width: '10%', alignSelf: 'center'}}
                                        onClick={onClickUpdate}>Submit</Button>
                            </>
                        ) : (
                            <>
                                <Typography sx={{p: 1}}>{formData.content}</Typography>
                                {formData.picture !== '' && formData.picture != null &&
                                    <Image sx={{p: 1}} src={formData.picture} alt={'unavailable'} height={'auto'}
                                           width={'auto'} fit={'scale-down'} duration={0}/>}
                                {success &&
                                    <Typography
                                        sx={{color: 'success.main', alignSelf: 'center'}}>{success}</Typography>}
                            </>
                        )}
                        <Typography sx={{p: 1}}>Date: {props.date}</Typography>
                        {props.token && (('' + props.userId) === props.token || props.moderator === 'true') && !isEditing &&
                            <Box sx={{display: 'flex', flexDirection: 'row'}}>
                                <IconButton aria-label='edit' sx={{width: '10%', alignSelf: 'left'}} onClick={() => {
                                    setIsEditing(true)
                                }}>
                                    <EditIcon/>
                                </IconButton>
                                <IconButton aria-label='delete' sx={{width: '10%', alignSelf: 'left'}} onClick={onClickDelete}>
                                    <DeleteIcon/>
                                </IconButton>
                            </Box>
                        }
                    </Box>
                </Box>
            </ThemeProvider>
        </Container>
    );
}

export default DisplayAnswer;