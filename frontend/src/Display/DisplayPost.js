import {Box, Container, Link, ThemeProvider, Typography} from "@mui/material";

const DisplayPost = (props) => {
    return (
        <Container sx={{p:1, textDecoration: 'none'}} component={Link} href={props.href}>
            <ThemeProvider theme={props.theme}>
                <Box sx={{display: 'flex', flexDirection: 'column', border: 10, borderColor: `${props.borderColor}`, p: 2, bgcolor: `${props.bgColor}`}}>
                    <Typography variant={'h4'} sx={{p: 1, color: 'primary.contrastText'}}>{props.title}</Typography>
                    <Typography sx={{p: 1, color: 'primary.contrastText'}}>{props.content}</Typography>
                </Box>
            </ThemeProvider>
        </Container>
    )
}

export default DisplayPost;