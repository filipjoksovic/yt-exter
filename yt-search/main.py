from fastapi import FastAPI
from pydantic import BaseModel
from pytube import Search, YouTube
from fastapi.middleware.cors import CORSMiddleware

origins = [
    "http://localhost.tiangolo.com",
    "https://localhost.tiangolo.com",
    "http://localhost",
    "http://localhost:8080",
    "http://localhost:4200",

]

app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
search_term: str = ""
search_instance: Search = None


class YoutubeDetailsRequest(BaseModel):
    video_url: str


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}


@app.get("/search")
async def search(term: str = ""):
    search_instance = Search(term)
    mapped_results = []
    return search_instance.results
    for result in search_instance.results:
        print(result)
        mapped_result = {
            "title": result.title,
            "video_id": result.video_id,
            "watch_url": result.watch_url,
            "embed_url": result.embed_url,
            "thumbnail_url": result.thumbnail_url,
            "author": result.author,
            "length": result.length
        }
        mapped_results.append(mapped_result)
    return mapped_results


@app.get("/search/next")
async def search_next():
    if search_instance is not None:
        return search_instance.get_next_results()
    else:
        return []


@app.post("/details")
async def details(video_details: YoutubeDetailsRequest):
    video = YouTube(video_details.video_url)
    return {
        "embed_url": video.embed_url,
        "title": video.title,
        "description": video.description,
        "thumbnail_url": video.thumbnail_url,
        "author": video.author,
        "video_id": video.video_id,
        "age_restricted": video.age_restricted
    }


@app.get("/search/mapped")
async def search(term: str = ""):
    search_instance = Search(term)
    mapped_results = []
    # return search_instance.results
    for result in search_instance.results:
        # print(result)
        mapped_result = {
            "title": result.title,
            "video_id": result.video_id,
            "watch_url": result.watch_url,
            "embed_url": result.embed_url,
            "thumbnail_url": result.thumbnail_url,
            "author": result.author,
            "length": result.length
        }
        mapped_results.append(mapped_result)
    return mapped_results

