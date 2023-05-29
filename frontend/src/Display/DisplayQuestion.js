import {Avatar, Box, Button, Container, IconButton, Link, TextField, ThemeProvider, Typography} from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import Image from "mui-image";
import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const DisplayQuestion = (props) => {
    const navigate = useNavigate();

    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState({
        questionId: props.questionId,
        title: props.title,
        content: props.content,
        picture: props.picture,
        tags: props.tags,
        votes: props.votes,
    });
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const onClickUpdate = () => {
        if (formData.title !== null && formData.content !== null && formData.title !== "" && formData.content !== "") {
            //console.log(formData);
            axios.put('http://localhost:8080/questions/updateQuestion', formData)
                .then((res) => {
                    if (res.status === 200) {
                        setSuccess("Successfully updated question");
                        setError("")
                        setIsEditing(false);
                    }
                    //console.log(res.data);
                })
                .catch((error) => {
                    console.log("An error occurred:", error);
                });
        } else {
            setError("Title and Content are required");
            setSuccess("")
        }
    }

    const onClickDelete = () => {
        axios.delete(`http://localhost:8080/questions/deleteById/${props.questionId}`)
            .then((res) => {
                if (res.status === 200) {
                    setSuccess("Successfully deleted question");
                    setError("");
                    setTimeout(() => {
                        navigate('/')
                    }, 5000);
                }
                //console.log(res.data);
            })
            .catch((error) => {
                console.log("An error occurred:", error);
            });
    }

    const toTagsList = (str) => {
        if (!str) {
            return []; // Return an empty list if the string is null or empty
        }

        return str.split(',').map(item => item.trim());
    }

    const getVote = () => {
        if (props.token == null) {
            return null;
        }
        const vote = formData.votes.find(value => value.userId === parseInt(props.token));
        //console.log(vote)
        return vote ? vote.positiveVote : null;
    }

    const onClickUpvote = () => {
        if (props.userId === parseInt(props.token)) {
            return;
        }
        //console.log(formData);
        axios.put('http://localhost:8080/questions/updateQuestion', ((getVote() !== null && getVote() === true) ?
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
        if (props.userId === parseInt(props.token)) {
            return;
        }
        // console.log(formData);
        axios.put('http://localhost:8080/questions/updateQuestion', ((getVote() !== null && getVote() === false) ?
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
                <Box sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    border: 10,
                    borderColor: 'primary.dark',
                    p: 2,
                    bgcolor: 'secondary.light'
                }}>
                    {isEditing ? (
                        <>
                            <TextField id="title" label="Title" variant="outlined" required
                                       defaultValue={formData.title}
                                       sx={{m: 1, alignSelf: 'center'}}
                                       onChange={(e) => setFormData({...formData, title: e.target.value})}/>
                            <TextField id="content" label="Content" variant="outlined" required multiline
                                       defaultValue={formData.content}
                                       sx={{m: 1, alignSelf: 'center'}}
                                       onChange={(e) => setFormData({...formData, content: e.target.value})}/>
                            <TextField id="picture" label="Picture Link" variant="outlined"
                                       defaultValue={formData.picture}
                                       sx={{m: 1, alignSelf: 'center'}}
                                       onChange={(e) => setFormData({...formData, picture: e.target.value})}/>
                            <TextField id="tags" label="Tags (Comma separated)" variant="outlined"
                                       defaultValue={formData.tags.join(',')}
                                       sx={{m: 1, alignSelf: 'center'}}
                                       onChange={(e) => setFormData({...formData, tags: toTagsList(e.target.value)})}/>
                            {error && <Typography sx={{color: 'error.main', alignSelf: 'center'}}>{error}</Typography>}
                            <Button variant="contained" size="medium" sx={{m: 1, width: '10%', alignSelf: 'center'}}
                                    onClick={onClickUpdate}>Submit</Button>
                        </>
                    ) : (
                        <>
                            <Typography variant={'h3'} sx={{p: 1}}>{formData.title}</Typography>
                            <Typography sx={{p: 1}}>{formData.content}</Typography>
                            {formData.picture !== '' && formData.picture != null &&
                                <Image sx={{p: 1}} src={formData.picture} alt={'unavailable'} height={'auto'}
                                       width={'auto'} fit={'scale-down'} duration={0}/>
                            }
                            {formData.tags.length > 0 &&
                                <Typography sx={{p: 1}}>
                                    Tags: {formData.tags
                                    .map((tag) => (
                                        <Link key={tag} href={`/tag/${tag}`}
                                              sx={{textDecoration: 'none', color: 'primary.contrastText'}}>{tag}</Link>
                                    ))
                                    .reduce((prev, curr) => [prev, ', ', curr])
                                }
                                </Typography>}
                            {success &&
                                <Typography sx={{color: 'success.main', alignSelf: 'center'}}>{success}</Typography>}
                        </>)}
                    <Typography sx={{p: 1}}>Date: {props.date}</Typography>
                    <Box sx={{display: 'flex', flexDirection: 'row'}}>
                        <Box sx={{width: '20%'}}>
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
                        <Box sx={{display: 'flex', flexDirection: 'column', textDecoration: 'none', width: '80%'}}
                             component={Link} href={`/users/${props.userId}`}>
                            <Avatar src={props.userPicture} alt={'unavailable'}
                                    sx={{width: '5%', height: 'auto', alignSelf: 'center', p: 2}} variant='square'/>
                            <Typography sx={{
                                alignSelf: 'center',
                                color: 'primary.contrastText'
                            }}>{props.userFirstName}</Typography>
                            <Typography sx={{
                                alignSelf: 'center',
                                color: 'primary.contrastText'
                            }}>{props.userLastName}</Typography>
                        </Box>
                    </Box>
                    {props.token && ('' + props.userId) === props.token && !isEditing &&
                        <Box sx={{display: 'flex', flexDirection: 'row'}}>
                            <IconButton aria-label='edit' sx={{width: '10%', alignSelf: 'left'}} onClick={() => {
                                setIsEditing(true)
                            }}>
                                <EditIcon/>
                            </IconButton>
                            <IconButton aria-label='delete' sx={{width: '10%', alignSelf: 'left'}}
                                        onClick={onClickDelete}>
                                <DeleteIcon/>
                            </IconButton>
                        </Box>
                    }
                </Box>
            </ThemeProvider>
        </Container>
    )
}

export default DisplayQuestion;