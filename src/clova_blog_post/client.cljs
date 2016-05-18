(ns clova-blog-post.client
(:require [clova.core :as clova]
          [clova-blog-post.validation :refer [to-do-validation]]
          [goog.dom :as dom]))

(enable-console-print!)

(defn validate
  []
  (let [title (.-value (dom/getElement "title"))
        date (.-value (dom/getElement "date"))
        category (.-value (dom/getElement "category"))
        validated (clova/validate to-do-validation {:title title
                                                    :date date
                                                    :meta {:category category}})]
    (set! (.-value (dom/getElement "valid")) validated)))

(.addEventListener (dom/getElement "validate") "click" #(validate))
