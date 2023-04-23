import {Avatar, Box, Button, Container, Link, ThemeProvider, Typography} from "@mui/material";
import Image from "mui-image";

const DisplayAnswer = (props) => {
    return (
        <Container sx={{p:1}}>
            <ThemeProvider theme={props.theme}>
                <Box sx={{display: 'flex', flexDirection: 'row', border: 10, borderColor: 'primary.main', p: 0}}>
                    <Box sx={{width: '20%', bgcolor: 'secondary.dark', textAlign: 'center', display: 'flex', flexDirection: 'column', p: 2}}>
                        <Box sx={{display: 'flex', flexDirection: 'column', textDecoration: 'none'}} component={Link} href={`/users/${props.userId}`}>
                            <Avatar src={props.userPicture} alt={'unavailable'} sx={{ width: '60%', height: 'auto', alignSelf: 'center', p: 2}} variant='square'/>
                            <Typography sx={{alignSelf: 'center', color: 'primary.contrastText'}}>{props.userFirstName}</Typography>
                            <Typography sx={{alignSelf: 'center', color: 'primary.contrastText'}}>{props.userLastName}</Typography>
                        </Box>
                        <Button sx={{width: '20%', alignSelf: 'center'}}>
                            <Image src={'https://styles.redditmedia.com/t5_2qnty/styles/postUpvoteIconInactive_n5ydt0uuj6x11.png'} duration={0}/>
                        </Button>
                        <Typography>Vote: {props.votes}</Typography>
                        <Button sx={{width: '20%', alignSelf: 'center'}}><Image src={'https://styles.redditmedia.com/t5_2qnty/styles/postDownvoteIconInactive_cnbj1c0wj6x11.png'} duration={0}/></Button>
                    </Box>
                    <Box sx={{width: '80%', bgcolor: 'secondary.main', p: 2}}>
                        <Typography sx={{p: 1}}>{props.content}</Typography>
                        {props.picture !== '' && props.picture != null && <Image sx={{p: 1}} src={props.picture} alt={'unavailable'} height={'auto'} width={'auto'} fit={'scale-down'} duration={0}/>}
                        <Typography sx={{p: 1}}>Date: {props.date}</Typography>
                    </Box>
                </Box>
            </ThemeProvider>
        </Container>
    );
}

export default DisplayAnswer;