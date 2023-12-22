import fastapi from "../apis/fastapi";

export const findSimilarById = (params) => {
    return fastapi.post('/similarity-search/findById', params);
};

export const findSimilarByPrompt = (params) => {
    return fastapi.post('/similarity-search/findByPrompt', params);
};

export const createSocialGraphFigure = (params) => {
    return fastapi.post('/plotly/create-figure', params);
};