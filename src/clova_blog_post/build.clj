(require 'cljs.build.api)

(cljs.build.api/build "src"
  {:main 'clova-blog-post.client
   :output-to "out/main.js"})
