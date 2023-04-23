import {Avatar, Box, Button, Container, Link, ThemeProvider, Typography} from "@mui/material";
import Image from "mui-image";

const DisplayQuestion = (props) => {
    return (
        <Container sx={{p:1}}>
            <ThemeProvider theme={props.theme}>
                <Box sx={{display: 'flex', flexDirection: 'column', border: 10, borderColor: 'primary.dark', p: 2, bgcolor: 'secondary.light'}}>
                    <Typography variant={'h3'} sx={{p: 1}}>{props.title}</Typography>
                    <Typography sx={{p: 1}}>{props.content}</Typography>
                    {props.picture !== '' && props.picture != null && <Image sx={{p: 1}} src={props.picture} alt={'unavailable'} height={'auto'} width={'auto'} fit={'scale-down'} duration={0}/>}
                    <Typography sx={{p: 1}}>
                        Tags: {props.tags
                            .map((tag) => (
                                <Link key={tag} href={'https://styles.redditmedia.com/t5_2qnty/styles/postUpvoteIconInactive_n5ydt0uuj6x11.png'} sx={{textDecoration: 'none', color: 'primary.contrastText'}}>{tag}</Link>
                            ))
                        .reduce((prev, curr) => [prev, ', ', curr])
                        }
                    </Typography>
                    <Typography sx={{p: 1}}>Date: {props.date}</Typography>
                    <Box sx={{display: 'flex', flexDirection: 'row'}}>
                        <Box sx={{width: '20%'}}>
                            <Button sx={{width: '20%', alignSelf: 'center'}}>
                                <Image src={'https://styles.redditmedia.com/t5_2qnty/styles/postUpvoteIconInactive_n5ydt0uuj6x11.png'} duration={0}/>
                            </Button>
                            <Typography>Vote: {props.vote}</Typography>
                            <Button sx={{width: '20%', alignSelf: 'center'}}><Image src={'https://styles.redditmedia.com/t5_2qnty/styles/postDownvoteIconInactive_cnbj1c0wj6x11.png'} duration={0}/></Button>
                        </Box>
                        <Box sx={{display: 'flex', flexDirection: 'column', textDecoration: 'none', width: '80%'}} component={Link} href={`/users/${props.userId}`}>
                            <Avatar src={props.userPicture} alt={'unavailable'} sx={{ width: '5%', height: 'auto', alignSelf: 'center', p: 2}} variant='square'/>
                            <Typography sx={{alignSelf: 'center', color: 'primary.contrastText'}}>{props.userFirstName}</Typography>
                            <Typography sx={{alignSelf: 'center', color: 'primary.contrastText'}}>{props.userLastName}</Typography>
                        </Box>
                    </Box>
                </Box>
            </ThemeProvider>
        </Container>
    );
}

export default DisplayQuestion;