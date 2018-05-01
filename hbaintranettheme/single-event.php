<?php
/**
 * The template for displaying a single event
 *
 * Please note that since 1.7, this template is not used by default. You can edit the 'event details'
 * by using the event-meta-event-single.php template.
 *
 * Or you can edit the entire single event template by creating a single-event.php template
 * in your theme. You can use this template as a guide.
 *
 * For a list of available functions (outputting dates, venue details etc) see http://codex.wp-event-organiser.com/
 *
 ***************** NOTICE: *****************
 *  Do not make changes to this file. Any changes made to this file
 * will be overwritten if the plug-in is updated.
 *
 * To overwrite this template with your own, make a copy of it (with the same name)
 * in your theme directory. See http://docs.wp-event-organiser.com/theme-integration for more information
 *
 * WordPress will automatically prioritise the template in your theme directory.
 ***************** NOTICE: *****************
 *
 * @package Event Organiser (plug-in)
 * @since 1.0.0
 */

//Call the template header
get_header(); ?>

<div class="main-content">

	<div class="bread"><a href="<?php echo esc_url( home_url( '/' ) ); ?>">Home</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a href="<?php echo esc_url( home_url( '/' ) ); ?>events/category/all/">Events</a>&nbsp;&nbsp;/&nbsp;&nbsp;<?php the_title(); ?></div>

	<div class="lhs-menu">
	<?php if ( has_nav_menu( 'news-menu' ) || has_nav_menu( 'social' ) ) : ?>
			<div class="menu">
				<?php if ( has_nav_menu( 'news-menu' ) ) : ?>
					<?php
						wp_nav_menu( array(
							'theme_location' => 'news-menu',
							'menu_class'     => 'news-menu',
						 ) );
					?>
				<?php endif; ?>
			</div>
		<?php endif; ?>
	</div>

	<div class="center-content">

		<?php while ( have_posts() ) : the_post(); ?>

			<article id="post-<?php the_ID(); ?>" class="news-item">

			<div class="content-head">
				<?php eo_get_template_part( 'event-meta', 'event-single' ); ?>
				<!-- Display event title -->
				<h2 class="entry-title"><?php the_title(); ?></h2>

			</div>

			<div class="content-middle">
				<!-- The content or the description of the event-->
				<div class="ww">
					<?php
						the_content();

						wp_link_pages( array(
							'before'      => '<div class="page-links"><span class="page-links-title">' . __( 'Pages:', 'hbaintranettheme' ) . '</span>',
							'after'       => '</div>',
							'link_before' => '<span>',
							'link_after'  => '</span>',
							'pagelink'    => '<span class="screen-reader-text">' . __( 'Page', 'hbaintranettheme' ) . ' </span>%',
							'separator'   => '<span class="screen-reader-text">, </span>',
						) );

						if ( '' !== get_the_author_meta( 'description' ) ) {
							get_template_part( 'template-parts/biography' );
						}

						// If comments are open or we have at least one comment, load up the comment template.
						if ( comments_open() || get_comments_number() ) {
							comments_template();
						}
					?>
				</div>

			</div><!-- .entry-content -->

			</article><!-- #post-<?php the_ID(); ?> -->			

		<?php endwhile; // end of the loop. ?>


	</div>

	<div class="rhs-content">
		
	</div>


</div>

<?php get_footer(); ?>



