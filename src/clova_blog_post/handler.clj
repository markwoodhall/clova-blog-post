(ns clova-blog-post.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [clova.core :as clova]
            [clova-blog-post.validation :refer [to-do-validation]]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-params]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn allow-cross-origin
  "middleware function to allow crosss origin"
  [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Access-Control-Allow-Origin"]
                "*"))))

(defn options-200
  "middleware function to always 200 an OPTIONS request"
  [handler]
  (fn [request]
    (if (= :options (:request-method request))
      {:headers {"Access-Control-Allow-Origin" "*"
                 "Access-Control-Allow-Methods" "GET, POST, PUT, OPTIONS"}
       :body ""
       :status 204}
      (handler request))))

(defroutes app-routes
  (POST "/to-do" [payload]
        (let [validated (clova/validate to-do-validation payload)]
          (if (:valid? validated)
            {:body payload :status 200}
            {:body (:results validated) :status 400})))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (options-200)
      (allow-cross-origin)
      (wrap-json-response)
      (wrap-json-params)))
