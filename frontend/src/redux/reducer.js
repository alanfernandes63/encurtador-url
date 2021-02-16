import { createStore, applyMiddleware, combineReducers } from 'redux';
import thunk from 'redux-thunk';

function urlReducer(state ={urls:[]} , action) {
  switch (action.type) {
    case 'ADD':
      return {...state, urls:[...state.urls, action.url]};
    case 'ADD_ALL':
      return { urls: [...action.urls] }
    default:
      return state
  }
}

function updateSnackbar(state =false , action) {
  switch (action.type) {
    case 'UPDATE':
      return action.open;
    default:
      return state
  }
}

function updateMessage(state='', action){
  switch (action.type) {
    case 'UPDATE_MESSAGE':
      return action.message;
    default:
      return state
  }
}

let store = createStore(combineReducers({urlReducer, updateSnackbar, updateMessage}), applyMiddleware(thunk));

export default store;