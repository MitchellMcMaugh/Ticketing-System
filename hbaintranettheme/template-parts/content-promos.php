<?php
/**
 * The template part for displaying content
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */
?>

<div class="promo" id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
	<?php if( get_field('promo_image') ): ?>
	<div class="promo-image" style="background-image: url(<?php the_field('promo_image'); ?>);">
		<?php $title = get_the_title(); ?>
		<a href="<?php the_permalink(); ?>"><h4><?php the_title(); ?></h4></a>
		<!-- echo mb_strimwidth($title, 0, 70, '...'); -->
	</div>
	<?php endif; ?>
	<a class="comments" title="Comments" href="<?php echo get_permalink(); ?>#respond"><?php comments_number( '0', '1', '%' ); ?></a>
	<?php the_title( sprintf( '<a href="%s#respond" rel="bookmark"><div class="comment-hide">', esc_url( get_permalink() ) ), '</div><div class="comment-show">Say something ...</div></a>' ); ?>
	<?php global $userdata; get_currentuserinfo(); echo get_avatar( $userdata->ID, 100 ); ?>
</div><!-- #post-## -->


