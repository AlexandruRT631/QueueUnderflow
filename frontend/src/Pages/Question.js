import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import DisplayAnswer from "../Display/DisplayAnswer";
import {Container, Typography, Box, ThemeProvider} from "@mui/material";
import DisplayQuestion from "../Display/DisplayQuestion";
import DisplayBar from "../Display/DisplayBar";
import DisplayNewAnswer from "../Display/DisplayNewAnswer";

const Question = (props) => {
    const [loaded, setLoaded] = useState(false)
    const [question, setQuestion] = useState({
        id: 0,
        title: "defaultTitle",
        content: "defaultContent",
        userId: 0,
        userFirstName: "defaultUserFirstName",
        userLastName: "defaultUserLastName",
        userPicture: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
        date: "defaultDate",
        picture: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
        votes: [],
        answers: [],
        tags: []
    })
    const {id} = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/questions/getById/${id}`)
            .then(res => {
                if (res.data) {
                    setQuestion(res.data)
                } else {
                    setQuestion(null)
                }
                setLoaded(true)
                //console.log(question)
            })
            .catch(err => console.log(err))
    })

    return loaded ? (
        <>
            <DisplayBar theme={props.theme} token={props.token}/>
            <Container sx={{p: 1}}>
                {question ? (
                    <>
                        <DisplayQuestion questionId={question.id}
                                         theme={props.theme}
                                         title={question.title}
                                         content={question.content}
                                         picture={question.picture}
                                         tags={question.tags}
                                         date={question.date}
                                         votes={question.votes}
                                         vote={question.votes.map(value => value.positiveVote).reduce((acc, cur) => acc + (cur ? 1 : -1), 0)}
                                         userId={question.userId}
                                         userPicture={question.userPicture}
                                         userFirstName={question.userFirstName}
                                         userLastName={question.userLastName}
                                         token={props.token}
                        />
                        {props.token &&
                        <DisplayNewAnswer theme={props.theme}
                                          questionId={question.id}
                                          token={props.token}
                                          />
                        }
                        {question.answers.map(answer => (<DisplayAnswer key={answer.id}
                                                                        answerId={answer.id}
                                                                        questionId={question.id}
                                                                        theme={props.theme}
                                                                        userId={answer.userId}
                                                                        userPicture={answer.userPicture}
                                                                        userFirstName={answer.userFirstName}
                                                                        userLastName={answer.userLastName}
                                                                        votes={answer.votes}
                                                                        vote={answer.votes.map(value => value.positiveVote).reduce((acc, cur) => acc + (cur ? 1 : -1), 0)}
                                                                        content={answer.content}
                                                                        picture={answer.picture}
                                                                        date={answer.date}
                                                                        token={props.token}
                        />))}</>
                ) : (
                    <ThemeProvider theme={props.theme}>
                        <Box sx={{
                            display: 'flex',
                            flexDirection: 'column',
                            border: 10,
                            borderColor: 'primary.dark',
                            p: 2,
                            bgcolor: 'secondary.light'
                        }}>
                            <Typography variant={'h4'} sx={{
                                p: 1,
                                color: 'primary.contrastText',
                                alignSelf: 'center'
                            }}>
                                Question not found
                            </Typography>
                        </Box>
                    </ThemeProvider>
                )}
            </Container>
        </>
    ) : (<div>Loading</div>)
}

export default Question