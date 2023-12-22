import {
    CHANGE_SEARCH_MENU,
    CHANGE_SEARCH_PARAMS,
    SELECT_AUTHOR,
    UNSELECT_AUTHOR,
    FETCH_SCIENTISTS_BY_NAME_LIKE,
    FETCH_SCIENTISTS_BY_SCOPUS_ID,
    FETCH_SCIENTISTS_BY_NAME_CONTAINING,
    FETCH_SCIENTISTS_BY_NAME_LIKE_ERROR,
    FETCH_SCIENTISTS_BY_SCOPUS_ID_ERROR,
    FETCH_SCIENTISTS_BY_NAME_CONTAINING_ERROR,
    FETCH_SIMILAR_BY_ID,
    FETCH_SIMILAR_BY_ID_ERROR,
    AUTHOR_SEARCH_ERROR_REMOVAL,
    SIMILARITY_SEARCH_ERROR_REMOVAL,
    CREATE_SOCIAL_GRAPH_FIGURE,
    CREATE_SOCIAL_GRAPH_FIGURE_ERROR,
    SOCIAL_GRAPH_FIGURE_ERROR_REMOVAL,
    FETCH_SIMILAR_BY_PROMPT,
    FETCH_SIMILAR_BY_PROMPT_ERROR, CHANGE_SIMILARITY_SEARCH_PARAMS
} from "./types";
import {
    findByNameLike,
    findByScopusId,
    findByNameContaining
} from "../services/AuthorService";
import {
    findSimilarById,
    createSocialGraphFigure,
    findSimilarByPrompt
} from "../services/SimilarityService";

export const changeSearchMenu = (searchMenuIndex) => {
    return {
        type: CHANGE_SEARCH_MENU,
        payload: searchMenuIndex
    };
};

export const changeSearchParams = (searchParams) => {
    return {
        type: CHANGE_SEARCH_PARAMS,
        payload: searchParams
    };
};

export const changeSimilaritySearchParams = (similaritySearchParams, menuIndex) => {
    return {
        type: CHANGE_SIMILARITY_SEARCH_PARAMS,
        payload: similaritySearchParams,
        menuIndex: menuIndex
    };
};

export const selectAuthor = (menuIndex, authorId, authorName) => {
    const payload = {menuIndex, authorId, authorName};

    return {
        type: SELECT_AUTHOR,
        payload: payload
    };
};

export const unselectAuthor = (menuIndex) => {
    return {
        type: UNSELECT_AUTHOR,
        payload: menuIndex
    };
};

export const fetchScientistsByNameLike = (params) => dispatch => {
    findByNameLike(params)
        .then(response => dispatch({
            type: FETCH_SCIENTISTS_BY_NAME_LIKE,
            payload: response.data
        }))
        .catch(error => dispatch(errorActionCreator(FETCH_SCIENTISTS_BY_NAME_LIKE_ERROR, error)));
};

export const fetchScientistsByScopusId = (scopusId) =>  dispatch => {
    const params = {
        scopusId: scopusId
    };

    findByScopusId(params)
        .then(response => dispatch({
            type: FETCH_SCIENTISTS_BY_SCOPUS_ID,
            payload: response.data
        }))
        .catch(error => dispatch(errorActionCreator(FETCH_SCIENTISTS_BY_SCOPUS_ID_ERROR, error)));
};

export const fetchScientistsByNameContaining = (params) =>  dispatch => {
    findByNameContaining(params)
        .then(response => dispatch({
            type: FETCH_SCIENTISTS_BY_NAME_CONTAINING,
            payload: response.data
        }))
        .catch(error => dispatch(errorActionCreator(FETCH_SCIENTISTS_BY_NAME_CONTAINING_ERROR, error)));
};

export const fetchSimilarById = (params, menuIndex = null, authorName = null, authorId) => dispatch => {
    dispatch({
        type: FETCH_SIMILAR_BY_ID,
        payload: {menuIndex: menuIndex, isLoading: true}
    });

    findSimilarById(params)
        .then(response => dispatch({
            type: FETCH_SIMILAR_BY_ID,
            payload: {...response.data, menuIndex: menuIndex, isLoading: false, authorName: authorName, authorId: authorId}
        }))
        .catch(error => dispatch(errorActionCreator(FETCH_SIMILAR_BY_ID_ERROR, error, menuIndex)));
};

export const fetchSimilarByPrompt = (params, menuIndex = null) => dispatch => {
    dispatch({
        type: FETCH_SIMILAR_BY_PROMPT,
        payload: {menuIndex: menuIndex, isLoading: true}
    });

    findSimilarByPrompt(params)
        .then(response => dispatch({
            type: FETCH_SIMILAR_BY_PROMPT,
            payload: {...response.data, menuIndex: menuIndex, isLoading: false}
        }))
        .catch(error => dispatch(errorActionCreator(FETCH_SIMILAR_BY_PROMPT_ERROR, error, menuIndex)));
}

export const fetchSocialGraphFigure = (params, menuIndex = null) => dispatch => {
    dispatch({
        type: CREATE_SOCIAL_GRAPH_FIGURE,
        payload: {menuIndex: menuIndex, isLoading: true}
    });

    createSocialGraphFigure(params)
        .then(response => {
            const data = JSON.parse(response.data);
            dispatch({
                type: CREATE_SOCIAL_GRAPH_FIGURE,
                payload: {...data, menuIndex: menuIndex, isLoading: false}
            });
        })
        .catch(error => dispatch(errorActionCreator(CREATE_SOCIAL_GRAPH_FIGURE_ERROR, error, menuIndex)));
};

export const authorSearchErrorRemoval = (menuIndex) => {
    return {
        type: AUTHOR_SEARCH_ERROR_REMOVAL,
        payload: menuIndex
    };
};

export const similaritySearchErrorRemoval = (menuIndex) => {
    return {
        type: SIMILARITY_SEARCH_ERROR_REMOVAL,
        payload: menuIndex
    };
};

export const socialGraphFigureErrorRemoval = (menuIndex) => {
    return {
        type: SOCIAL_GRAPH_FIGURE_ERROR_REMOVAL,
        payload: menuIndex
    };
};

export const errorActionCreator = (errorType, error, menuIndex = null) => {
    return {
        type: errorType,
        payload: {
            error: true,
            message: error?.message,
            status: error?.status,
            menuIndex: menuIndex,
            isLoading: false
        }
    }
};