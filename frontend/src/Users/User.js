import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import {Avatar, Box, Container, ThemeProvider, Typography} from "@mui/material";
import DisplayPost from "./DisplayPost";

const User = (props) => {
    const [loaded, setLoaded] = useState(false)
    const [user, setUser] = useState({
        id: 0,
        firstName: "defaultFirstName",
        lastName: "defaultSecondName",
        picture: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
        moderator: false,
        banned: false,
        questions: [],
        answers: [],
    })
    const {id} = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/users/getById/${id}`)
            .then(res => {
                setUser(res.data)
                setLoaded(true)
                // console.log(res.data)
            })
            .catch(err => console.log(err))
    }, [id])

    return loaded ?
        (
            <Container sx={{p:1}}>
                <ThemeProvider theme={props.theme}>
                    <Box sx={{display: 'flex', flexDirection: 'row', border: 10, borderColor: 'primary.light', p: 0}}>
                        <Box sx={{width: '30%', bgcolor: 'secondary.dark', textAlign: 'center', display: 'flex', flexDirection: 'column', p: 2}}>
                            <Avatar src={user.picture} alt={'unavailable'} sx={{ width: '60%', height: 'auto', alignSelf: 'center', p: 2}} variant='square'/>
                            <Typography sx={{alignSelf: 'center', color: 'primary.contrastText'}}>{user.firstName}</Typography>
                            <Typography sx={{alignSelf: 'center', color: 'primary.contrastText'}}>{user.lastName}</Typography>
                            <Typography sx={{alignSelf: 'center', color: 'primary.contrastText', p: 2}}>Score: 0</Typography>
                            {user.moderator && <Typography sx={{alignSelf: 'center', color: 'primary.main'}}>Moderator</Typography>}
                            {user.banned && <Typography sx={{alignSelf: 'center', color: 'red'}}>Banned</Typography>}
                        </Box>
                        <Box sx={{width: '70%', bgcolor: 'secondary.main', p: 2}}>
                            {user.questions.length > 0 && (
                                <>
                                    <Typography sx={{color: 'primary.contrastText'}} variant={'h2'}>Questions</Typography>
                                    {user.questions.map(question => (
                                        <DisplayPost
                                            key={`q${question.id}`}
                                            id={question.id}
                                            theme={props.theme}
                                            borderColor={`primary.dark`}
                                            bgcolor={`secondary.light`}
                                            title={question.title}
                                            content={question.content}
                                            href={`/questions/${question.id}`}
                                        />
                                    ))}
                                </>
                            )}{user.answers.length > 0 && (
                            <>
                                <Typography sx={{color: 'primary.contrastText'}} variant={'h2'}>Answers</Typography>
                                {user.answers.map(answer => (
                                    <DisplayPost
                                        key={`a${answer.id}`}
                                        id={answer.id}
                                        theme={props.theme}
                                        borderColor={`primary.main`}
                                        bgcolor={`secondary.dark`}
                                        title={answer.questionTitle}
                                        content={answer.content}
                                        href={`/questions/${answer.questionId}`}
                                    />
                                ))}
                            </>
                        )}

                        </Box>
                    </Box>
                </ThemeProvider>
            </Container>
        ) : ( <div>Loading</div>)
}

export default User