---
layout: post
title:  "Integrate MathJax into Jekyll"
date:   2024-04-06 18:11:18 -0500
categories: github-pages Jekyll MathJax
---

Jekyll Markdown Support doesn't includes mathematical equations. If one wants to write mathematical equations, integrating
MathJax might be one possbile solution.

If you are also using minima as the theme, you can allow mathematical syntax in your post pages by performing following steps:
- Run `bundle info --path` followed by the name of the theme’s gem, e.g., `bundle info --path minima` for Jekyll’s default theme.
This returns the location of the gem-based theme files. For example, the Minima theme’s files might be located in /usr/local/lib/ruby/gems/2.6.0/gems/minima-2.5.1 on macOS.
- Open the themes directory, and find the `default.html` file under the `_layout folder`, `copy` that file into the _layouts folder of your project. If you have `no` `_layouts folder` under the `docs folder` in your project just like me, you might need to `create` that the _layouts folder.
- `Append` the following scirpts tag into the `default.html` file

{% highlight html %}
<script type="text/javascript"
    src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.3/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>
{% endhighlight %}

Now your default.html file under the _layouts folder might look like this:

![Defaul.html](/assets/img/defaul-html.png)


Then your post pages should be able to render mathematical equations properly.

![Mathematical Equations](/assets/img/mathematical-equations.png)